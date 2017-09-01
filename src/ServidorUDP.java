
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServidorUDP {
	public static void main(String[] args) throws Exception {
		int porta = 3737;
		byte[] dadosRecebidos = new byte[512];
		
		DatagramSocket socketServidor = new DatagramSocket(porta);
		System.out.println("Serviço de TIME UDP rodando na porta 3737... Aguardando requisições...");
		DateFormat df = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
		DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
		 
		while(true) {
			// Recebe pacote
			socketServidor.receive(pacoteRecebido);
			
			// Calcula hora em Milis
			long hora = System.currentTimeMillis();
			String mensagem = hora + "";
			
			// Extrai informações
			InetAddress clientDestino = pacoteRecebido.getAddress();
			int port = pacoteRecebido.getPort();
			
			// Debug
			Date data = new Date(hora);
			System.out.println("Hora solicitada pelo host " + clientDestino + ":" + port);
			System.out.println("Data  (" + df.format(data) + ") enviada no formato " + mensagem);
						
			
			// Constrói pacote de retorno
			byte[] mensagemRetorno = mensagem.getBytes();
			DatagramPacket pacoteRetorno = new DatagramPacket(mensagemRetorno, mensagemRetorno.length, clientDestino, port);
			
			// Envia dados de volta
			socketServidor.send(pacoteRetorno);	 
		}
	}
}