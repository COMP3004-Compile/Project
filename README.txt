Start Eclipse and open files: TFTPClient.java, TFTPServer.java, and TFTPSim.java.

Start the server first, i.e.: "Run as... Java Application".
Next start the simulator (intermediate host).
Finally start the client.
Each should have its own window where the progress is displayed.

The first 10 packets will involve messages being sent from/to: client, simulator, server, simulator, client.
The 11th (invalid) will go from: client, simulator, server; and the server will throw an exception and quit.
This means that the client and simulator will need to be manually stopped.

The design diagrams can be found in: SYSC_3303_Asst1_Sample_Solutions.pdf. 