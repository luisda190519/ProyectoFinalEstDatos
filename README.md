# me.Chat - Video Conferencing Application

me.Chat is a Java-based video conferencing application that provides features similar to Zoom or Microsoft Teams, including text chat, audio calls, and video calls.

## Features

- Text chat with real-time messaging
- Audio calls with multiple participants
- Video calls with camera support
- User profile pictures
- Mention notifications (with sound alert)
- Server-client architecture for multi-user support

## System Architecture

The application is divided into two main components: Server and Client.

### Server

The server handles client connections and manages the distribution of data between clients. It consists of several components:

1. **Server**: Main class that processes client requests and manages connections.
2. **Threads**:
   - Chat Thread: Handles text messages
   - Images Thread: Manages profile picture transmission
   - Camera Thread: Handles video transmission
   - BroadcastThread: Manages audio connections
   - ConexionAudio: Handles audio data transmission

### Client

The client provides the user interface and manages the connection to the server. Key components include:

1. **Client**: Main class with the user interface and connection management.
2. **Threads**:
   - Chat Thread: Sends and receives text messages
   - Images Thread: Handles profile picture transmission
   - Camera Thread: Manages video transmission
   - EnviarImagenCamara: Sends camera images to the server
   - AudioChannel: Plays received audio
   - Voice: Manages audio connections
   - Microphone: Captures and sends microphone data

## Technologies Used

- Java
- Java Swing (for GUI)
- Java Socket Programming
- Webcam Capture API (for camera access)
- Java Sound API (for audio processing)

## Getting Started

1. Compile the server and client code separately.
2. Run the server on a machine that will act as the host.
3. Run the client application on each participant's machine.
4. Connect to the server using its IP address and port number.

## Usage

- Start a meeting by initiating a server connection.
- Join a meeting by connecting to an existing server.
- Use the chat feature to send text messages.
- Toggle your camera on/off to participate in video calls.
- Use the audio features to participate in voice conversations.

![Interfaz](https://raw.githubusercontent.com/luisda190519/ProyectoFinalEstDatos/main/imagenes/interfaz.png)

![Interfaz2](https://raw.githubusercontent.com/luisda190519/ProyectoFinalEstDatos/main/imagenes/interfaz2.png)

![Interfaz3](https://raw.githubusercontent.com/luisda190519/ProyectoFinalEstDatos/main/imagenes/interfaz3.png)


## Contributing

Contributions to improve me.Chat are welcome. Please feel free to submit pull requests or open issues for bugs and feature requests.

## Authors

- Jaider Olivella Morales
- Luis Daniel Fuentes

## Acknowledgments

- Universidad del Norte, Barranquilla, Colombia

