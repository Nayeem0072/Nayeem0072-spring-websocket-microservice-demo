// src/App.js
import React, { useState, useEffect, useRef } from 'react';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './App.css';

function App() {
  const [messages, setMessages] = useState([]);
  const [inputMessage, setInputMessage] = useState('');
  const [connected, setConnected] = useState(false);
  const stompClient = useRef(null);

  useEffect(() => {
    console.log('Attempting to connect to WebSocket...');
    // Change the WebSocket connection URL to go through the gateway
    const socket = new SockJS('http://localhost:8080/chat');

    stompClient.current = Stomp.over(socket);

    stompClient.current.connect({}, 
      () => {
        console.log('Successfully connected to WebSocket');
        setConnected(true);
        stompClient.current.subscribe('/topic/messages', (message) => {
          console.log('Received message:', message);
          const receivedMessage = JSON.parse(message.body);
          setMessages((prevMessages) => [...prevMessages, receivedMessage]);
        });
      }, 
      (error) => {
        console.error('STOMP error:', error);
        setConnected(false);
      }
    );

    return () => {
      if (stompClient.current) {
        console.log('Disconnecting from WebSocket');
        stompClient.current.disconnect();
      }
    };
  }, []);

  const sendMessage = () => {
    if (inputMessage && stompClient.current && connected) {
      stompClient.current.send("/app/chat.sendMessage", {}, JSON.stringify({ content: inputMessage }));
      setInputMessage('');
    }
  };

  return (
    <div className="App">
      <h1>Chat App</h1>
      <div className="chat-container">
        {messages.map((msg, index) => (
          <div key={index} className="message">
            {msg.content}
          </div>
        ))}
      </div>
      <input
        type="text"
        value={inputMessage}
        onChange={(e) => setInputMessage(e.target.value)}
        onKeyPress={(e) => e.key === 'Enter' && sendMessage()}
        disabled={!connected}
      />
      <button onClick={sendMessage} disabled={!connected}>
        {connected ? 'Send' : 'Connecting...'}
      </button>
    </div>
  );
}

export default App;