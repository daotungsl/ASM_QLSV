package validate;

public class ErrEntity {

    private String errEmpty;
    private String errCharacter;
    private String errLength;
    private String errDuplicate;

    public ErrEntity() {
    }

    public ErrEntity(String errEmpty, String errCharacter, String errLength, String errDuplicate) {
        this.errEmpty = errEmpty;
        this.errCharacter = errCharacter;
        this.errLength = errLength;
        this.errDuplicate = errDuplicate;
    }

    public String getErrDuplicate() {
        return errDuplicate;
    }

    public void setErrDuplicate(String errDuplicate) {
        this.errDuplicate = errDuplicate;
    }

    public String getErrEmpty() {
        return errEmpty;
    }

    public void setErrEmpty(String errEmpty) {
        this.errEmpty = errEmpty;
    }

    public String getErrCharacter() {
        return errCharacter;
    }

    public void setErrCharacter(String errCharacter) {
        this.errCharacter = errCharacter;
    }

    public String getErrLength() {
        return errLength;
    }

    public void setErrLength(String errLength) {
        this.errLength = errLength;
    }
}
