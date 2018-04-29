/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "chat";
var websocket = new WebSocket(wsUri);

websocket.onmessage = function (evt) {
  onMessage(evt);
};

function sendText() {
  var textBox = $('#clienttext');
  if (textBox.val() !== "") {
    websocket.send(textBox.val());
    textBox.val('');
  } else {
    alert("Enter a value to send to server");
  }
}

function onMessage(evt) {
  addHistory(evt.data);
}

function addHistory(text) {
  var chatHistory = $("#chat");
  chatHistory.val(chatHistory.val() + "\n" + text);
}

