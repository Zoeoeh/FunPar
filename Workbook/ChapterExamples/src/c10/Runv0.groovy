package c10

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*
import phw.util.*

println "Starting v0 ..."
def nodes = Ask.Int ( "Number of Nodes ? - ", 3, 10 )

def ring = Channel.one2oneArray(nodes+1)

def extra = new ExtraElement ( fromRing: ring[0].in(),
                                toRing:   ring[1].out())   

def elementList = (1..nodes).collect{i -> 
                                      def toR = (i+1)%(nodes+1)
                                      println "Creating Element: ${i} from  ${i} to ${toR}"
                                      return new Elementv0 ( fromRing: ring[i].in(),
                                                              toRing: ring[toR].out(),
                                                              element: i,
                                                              nodes: nodes)
                                     }

new PAR (elementList + extra).run()
