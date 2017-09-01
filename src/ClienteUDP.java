
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
	public static void main(String[] args) throws Exception {
		
		Scanner reader = new Scanner(System.in);
		InetAddress hostDestino = InetAddress.getByName("localhost");
		DatagramSocket socketCliente = new DatagramSocket();
	
		// Lê mensagem
		System.out.println("Solicitando hora do servidor: ");
		String message = "";
		
		// Extrai bytes da mensagem
		byte[] dadosEnviados = message.getBytes();
		byte[] dadosRecebidos = new byte[100];
		// Constroi pacote de envio
		DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, hostDestino, 3737);
		
		// Envia mensagem
		socketCliente.send(pacoteEnviado);
		
		// Constri pacote de retorno e espera pela resposta
		DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length, hostDestino, 3736);
		socketCliente.receive(pacoteRecebido);
		
		// Extrai mensagem
		String mensagem = new String(dadosRecebidos);
		
		// Exibe mensagem 
		System.out.println("HORA RETORNADA: " + mensagem);
					
		socketCliente.close();
	}
}