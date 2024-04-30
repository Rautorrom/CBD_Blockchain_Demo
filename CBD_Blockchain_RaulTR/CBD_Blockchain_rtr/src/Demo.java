public class Demo {

    public static void main(String[] args) throws Exception {
    	
    	String actor1 = "Elon Musk";
    	String contenido1 = "Yo, Elon Musk, le regala el 80% de las acciones de Tesla a Raul Toro";
    	RSA rsaMusk = new RSA();
    	String firma1 = rsaMusk.firma(contenido1);

    	String actor2 = "Raúl Toro";
    	String contenido2 = "Yo, Raul Toro, acepto recibir el 80% de las acciones de Tesla de parte de Elon Musk";
    	RSA rsaToro = new RSA();
    	String firma2 = rsaToro.firma(contenido2);
    	
    	String version = "1.0";
    	
    	Transaction tx1 = new Transaction(actor1, contenido1, firma1, actor2, contenido2, firma2, version);
    	
    	// Creación del primer bloque: bloque génesis
    	Transaction[] stx = {};
    	Block bloqueGenesis = new Block(0, stx, version);
    	
    	// Creación del segundo bloque
    	Transaction[] listaTransacciones = {tx1};
    	Block bloquePrimero = new Block(bloqueGenesis.getBlockHash(), listaTransacciones, version);
    	
    	System.out.println(bloqueGenesis);
    	System.out.println(bloquePrimero);
    	
    }
}
