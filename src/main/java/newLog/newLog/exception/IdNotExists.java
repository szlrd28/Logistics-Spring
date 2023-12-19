package newLog.newLog.exception;

public class IdNotExists extends RuntimeException {

    public IdNotExists(long id, Class<?> clazz) {
        super(clazz.getSimpleName() + " not exists with id: "+id);
    }
}
