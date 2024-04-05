package coit132229_applied_distributed_systems_ass1;

import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer {
    private static final AtomicInteger _cclient = new AtomicInteger(0);
    private static ServerSocket _sSocket;

    public static void main(String[] args) {
        try {
            _sSocket = new ServerSocket(1114);

            // Add a shutdown hook to close the server socket when the application is terminated
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (_sSocket != null && !_sSocket.isClosed()) {
                        _sSocket.close();
                        System.out.println("Server socket closed.");
                    }
                } catch (IOException e) {
                }
            }));

            Timer tm = new Timer();
            tm.schedule(new TimerTask() {
                @Override
                public void run() {
                    SerializedDataObjects();
                }
            }, 1000,1000);

            while (true) {
                Socket cs = _sSocket.accept();
                int cNumber = _cclient.incrementAndGet();
                System.out.println("Receiving data from client: " + cNumber);
                 new Thread(new ClientHandler(cs, cNumber)).start();
                cNumber++;
                System.out.println("Receiving data from client: " + cNumber);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

      private static void SerializedDataObjects() {
        try (ObjectOutputStream oObjectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\Data\\memberlistObject.txt"))) {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\Data\\memberlist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.equals(""))
                {
                    String[] dt = line.split(":");
                    Member omember = new Member(dt[0], dt[1], dt[2], dt[3]);
                    oObjectOutputStream.writeObject(omember);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable 
   
    {
        private final Socket _cSocket;
        private final int _cNo;

        public ClientHandler(Socket socket, int _cNo) {
            this._cSocket = socket;
            this._cNo = _cNo;
        }
       
        
        
        private void MemberSave(String data) {
            try (FileWriter oFileWriter = new FileWriter("D:\\Data\\memberlist.txt", true);
                 BufferedWriter oBufferedWriter = new BufferedWriter(oFileWriter);
                 PrintWriter oPrintWriter = new PrintWriter(oBufferedWriter)) {
                oPrintWriter.println(data);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
        @Override
        public void run() {
            try (DataInputStream oDataInputStream = new DataInputStream(_cSocket.getInputStream());
                 DataOutputStream oDataOutputStream = new DataOutputStream(_cSocket.getOutputStream())) {
                String Detailsmember = oDataInputStream.readUTF();
                MemberSave(Detailsmember);
                oDataOutputStream.writeUTF("Save Data of the member number: ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        
    }
    
    
  
}
