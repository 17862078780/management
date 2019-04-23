import com.sunniwell.common.util.IdWorker;

public class IdWorkTest {

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, 1);
        long l = idWorker.nextId();
        System.out.println(l);

    }
}
