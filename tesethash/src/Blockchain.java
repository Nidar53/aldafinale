import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Blockchain {
    public int difficulty ;
    public static String index = "";
    Map<String,Block> Blocks = new HashMap<>();

    public Blockchain(int startDifficulty) {
        this.Blocks = new HashMap<>();
        this.difficulty = startDifficulty;

    }

    public synchronized void addBlock(Block blo) {
        if (Blocks.size() != 0 && !blo.getPreviousHash().equals(Blocks.get(Blocks.size() - 1).getHash())){
            index=Blocks.get(Blocks.size() -1).getHash();
            blo.previousHash=index;
            Blocks.put( index ,blo);
        }else {
            if(Blocks.size()==0) {
                index=null;
                blo.previousHash=index;
                Blocks.put(index, blo);
            }
        }
    }

    public synchronized String getLastHash() {
        if (Blocks.size() > 0) {
            return Blocks.get(Blocks.size() - 1).getHash();
        }
        return "";
    }

    public Map<String,Block>getBlocks() {
        return Collections.unmodifiableMap(Blocks);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean BlockchainisValid() {
        for (int i = 0; i < Blocks.size(); i++) {
            if (i == 0) {
                continue;
            }
            Block blo = Blocks.get(i);

            if (!blo.getPreviousHash().equals(Blocks.get(i - 1).getHash())) {
                return false;
            }
        }
        return true;
    }

    public Block latestBlock() {
        return Blocks.get(Blocks.size() - 1);
    }




}

