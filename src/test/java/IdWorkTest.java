import com.sunniwell.common.utils.IdWorker;

public class IdWorkTest {

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, 2);
        long l = idWorker.nextId();
        System.out.println(l);

    }
}
