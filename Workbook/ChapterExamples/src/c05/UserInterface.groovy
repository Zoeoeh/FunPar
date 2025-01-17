package c05

import org.jcsp.awt.*
import org.jcsp.groovy.*
import org.jcsp.lang.*
import java.awt.*

class UserInterface implements CSProcess
{

	// define inputs
	def ChannelInput toConsole
	def ChannelOutput fromConsole
	def ChannelInput clearInputArea
	
	def ChannelInput suspendButton
	def ChannelOutput suspend
	
	// title of console
	def String frameLabel = "Scaler Console"

	def void run()
	{
		// define UI frames		
		def main = new ActiveClosingFrame (frameLabel)
		def root = main.getActiveFrame()
		root.setLayout ( new BorderLayout () )
		
		// draw label
		def outLabel = new Label ("Scaled Output", Label.CENTER)
		outLabel.setFont(new Font("sans-serif", Font.BOLD, 20))
		def inLabel = new Label ("Input New Scale", Label.CENTER)
		inLabel.setFont(new Font("sans-serif", Font.BOLD, 20))
		
		// set output here
		def outText = new ActiveTextArea ( toConsole, null, "", 0, 0, java.awt.TextArea.SCROLLBARS_VERTICAL_ONLY)
		
		// input from console
		def inText = new ActiveTextEnterField ( clearInputArea, fromConsole )
		def console = new Container()
		console.setLayout ( new GridLayout ( 5, 1 ) )
		console.add ( outLabel )
		console.add ( outText )
		console.add ( inLabel )
		console.add ( inText.getActiveTextField() )
		
		def button = new ActiveButton (suspendButton, suspend, "SUSPEND")
		button.setFont(new Font("sans-serif", Font.BOLD, 20))
		console.add(button)
		
		root.add(console, BorderLayout.CENTER )
		root.pack()
		root.setVisible(true)
		def interfaceProcessList = [ main, outText, inText, button ]
		new PAR ( interfaceProcessList ).run()
		
	}
}
