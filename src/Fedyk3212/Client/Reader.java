package Fedyk3212.Client;

import Fedyk3212.Resources.Errors;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.swing.JTextArea;

public class Reader extends Thread {
    private Socket socket;

    private BufferedReader reader;

    private JTextArea jTextArea;

    public Reader(Socket socket, JTextArea jTextArea) throws IOException {
        this.jTextArea = jTextArea;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
    }

    public void run() {
        try {
            while (true) {
                String data = this.reader.readLine();
                if (Objects.equals(data, "NOREG")){
                    this.socket.close();
                    Errors.Show_NOREG_error();
                }
                if (data == null) {
                    this.jTextArea.append("\n");
                    this.socket.close();
                    break;
                }
                this.jTextArea.append(data + "\n");
            }
        } catch (IOException e) {}
    }
}