public class Transaction {

	private String actor1;
	private Object contenido1;
	private String firma1;
	
	private String actor2;
	private Object contenido2;
	private String firma2;
	
	private String version;
	private int hashTransaction;
	
	public Transaction(String actor1, Object contenido1, String firma1, String actor2, Object contenido2, String firma2,
			String version) {
		super();
		this.actor1 = actor1;
		this.contenido1 = contenido1;
		this.firma1 = firma1;
		this.actor2 = actor2;
		this.contenido2 = contenido2;
		this.firma2 = firma2;
		this.version = version;
		
		Object[] txContenido = {actor1, contenido1, firma1, actor2, contenido2, firma2, version};
		this.hashTransaction = txContenido.hashCode();
	}

	public String getActor1() {
		return actor1;
	}

	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}

	public Object getContenido1() {
		return contenido1;
	}

	public void setContenido1(Object contenido1) {
		this.contenido1 = contenido1;
	}

	public String getFirma1() {
		return firma1;
	}

	public void setFirma1(String firma1) {
		this.firma1 = firma1;
	}

	public String getActor2() {
		return actor2;
	}

	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}

	public Object getContenido2() {
		return contenido2;
	}

	public void setContenido2(Object contenido2) {
		this.contenido2 = contenido2;
	}

	public String getFirma2() {
		return firma2;
	}

	public void setFirma2(String firma2) {
		this.firma2 = firma2;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getHashTransaction() {
		return hashTransaction;
	}

	public void setHashTransaction(int hashTransaction) {
		this.hashTransaction = hashTransaction;
	}
	
	

}
