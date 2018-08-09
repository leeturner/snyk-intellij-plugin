package io.snyk.plugin.ui

import java.io.IOException

import com.sun.javafx.scene.web.Debugger
import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoWSD
import fi.iki.elonen.NanoWSD.WebSocketFrame.CloseCode
import fi.iki.elonen.NanoWSD.{WebSocket, WebSocketFrame}
import javafx.application.Platform
import javafx.scene.web.WebEngine

//class DebugWebSocketServer(engine: WebEngine, port0: Int = 0) extends NanoWSD(port0) {
//  start(0) //no timeout
//
//  val debugger: Debugger = engine.impl_getDebugger()
//
//  debugger.setEnabled(true)
//  debugger.sendMessage("""{"id" : -1, "method" : "Network.enable"}""")
//
//  var connectedSocket: Option[WebSocket] = None
//
//  debugger.setMessageCallback { message => connectedSocket.foreach(_ send message); null }
//
//  val remoteUrl = s"chrome-devtools://devtools/bundled/inspector.html?ws=localhost:${this.getListeningPort}/"
//  println(s"To debug open chrome and load next url: $remoteUrl")
//
//  override def openWebSocket(handshake: IHTTPSession): WebSocket = {
//    println(s"Opening websocket with handshake: $handshake")
//    val newSocket = new DebugWebSocket(handshake)
//    connectedSocket = Some(newSocket)
//    newSocket
//  }
//
//  private class DebugWebSocket(handshake: IHTTPSession) extends WebSocket(handshake) {
//    override def onOpen(): Unit = println(s"Opened websocket with handshake: $handshake")
//
//    override def onClose(code: CloseCode, reason: String, initiatedByRemote: Boolean): Unit = {
//      val initiatedBy: String = if(initiatedByRemote) "Remote" else "Self"
//      val reasonStr = Option(reason).filterNot(_.isEmpty).map(r => s"reason: $r").getOrElse("")
//      println(s"C [$initiatedBy] $code $reasonStr")
//    }
//
//    override def onMessage(frame: WebSocketFrame): Unit = {
//      frame.setUnmasked()
//      Platform.runLater{ () => debugger.sendMessage(frame.getTextPayload)}
//      println(frame.getTextPayload)
//    }
//
//    override def onPong(pong: WebSocketFrame): Unit = println(s"P $pong")
//
//    override def onException(exception: IOException): Unit = println(s"exception occurred $exception")
//  }
//}
