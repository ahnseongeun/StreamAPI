package Optional;

import java.util.Optional;

/**
 * 옵셔널이란
 */
public class OptionalClass {

    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;

    public OptionalClass(Integer id, String title, boolean closed){
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Progress getProgress(){
        return progress;
    }

    /**
     * @return
     */
    public Optional<Progress> getProgressByOptional(){
        return Optional.ofNullable(progress);
        // of와 ofNullable의 차이는
        // of 뒤에는 무조건 null이 아닌값이
        // ofNullable은 null일 수도 있는 값이 들어온다는 가정
    }

    /**
     * return 값이 null이면 안된다.
     * @return
     */
    public Optional<Progress> getProgressByOptional_empty(){
        return Optional.empty();
        //return null; 이렇게 하면 안된다.
    }


    public void setProgress(Progress progress){
        this.progress = progress;
    }

    /**
     * 이렇게 파라미터로 옵셔널을 사용하는 것은 안좋다.
     * 왜??
     * progress.setProgressByOptional(null)을 하게 된다면 nullPointException이 발생한다.
     * 즉, optional을 사용 안한 것과 같다.
     * @param progress
     */
    public void setProgressByOptional(Optional<Progress> progress){
        progress.ifPresent(p -> this.progress = p);
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }
}
