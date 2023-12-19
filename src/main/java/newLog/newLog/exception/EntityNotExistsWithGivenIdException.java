package newLog.newLog.exception;

public class EntityNotExistsWithGivenIdException extends RuntimeException {

    public EntityNotExistsWithGivenIdException(long id, Class<?> clazz) {
        super(clazz.getSimpleName() + " not exists with id: "+id);
    }
}
