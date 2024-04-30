import java.util.Arrays;
import java.util.Objects;

public class Block {

    public int prevHashBlock;
    public Transaction[] transactions;
    public int numTransactions;
    public int blockHash;
    public String version;
    private Integer nonce;

    public Block(int prevHashBlock, Transaction[] listaTransacciones, String version) {
        this.setPrevHashBlock(prevHashBlock);
        this.transactions = listaTransacciones;
        this.numTransactions = listaTransacciones.length;
        this.version = version;
        this.nonce = 0;

        Object[] contenido = {prevHashBlock, Arrays.hashCode(listaTransacciones), version};
        this.blockHash = minadoRecursivo(this.nonce, contenido);
    }

    // Minado de bloques

	private boolean bloqueNoMinado(Integer result) {
		Boolean res = true;
		res = ( result > 99999999) | (result % 10000 != 0);
		return res;
	}
	
	public int minadoRecursivo(Integer nonce, Object[] contenido) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(transactions);
		result = prime * result + Objects.hash(blockHash, numTransactions, prevHashBlock, version);
		result = prime * result * nonce.hashCode();
		if (result == 0 | bloqueNoMinado(result)) {
			Integer poximoNonce = nonce +1;
			result = minadoRecursivo(poximoNonce, contenido);
		}
		return result;
	}    
    
    // Getters & Setters
    
	public int getNumTransactions() {
		return numTransactions;
	}

	public void setNumTransactions(int numTransactions) {
		this.numTransactions = numTransactions;
	}

	public int getPrevHashBlock() {
		return prevHashBlock;
	}

	public void setPrevHashBlock(int prevHashBlock) {
		this.prevHashBlock = prevHashBlock;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}

	public int getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(int blockHash) {
		this.blockHash = blockHash;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Block [prevHashBlock=" + prevHashBlock + ", transactions=" + Arrays.toString(transactions)
				+ ", numTransactions=" + numTransactions + ", blockHash=" + blockHash + ", version=" + version + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		return blockHash == other.blockHash && numTransactions == other.numTransactions
				&& prevHashBlock == other.prevHashBlock && Arrays.equals(transactions, other.transactions)
				&& Objects.equals(version, other.version);
	}





}
