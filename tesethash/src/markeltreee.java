import java.nio.charset.StandardCharsets;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;


public class markeltreee {
    public static String calculateHash(String blo) {
        String hash = Hashing.sha256().hashString(blo, StandardCharsets.UTF_8).toString();
        return hash;
    }
    public  String merkleTree(List<String>file , int low , int high) {
        if(low==high) {
            String content=file.get(low);
            String hashcode=calculateHash(content);
            return hashcode;
        }
        int mid=low+(high-low)/2;
        String leftHashCode=merkleTree(file, low ,mid);
        String rightHashCode=merkleTree(file,mid+1 , high);

        return calculateHash(leftHashCode+""+rightHashCode);

    } }
