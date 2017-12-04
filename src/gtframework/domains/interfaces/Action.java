package gtframework.domains.interfaces;

/**
 * Created by YoungD3v on 4.12.2017.
 */
public interface Action<E> {

    Player getPlayer();
    String getName();
    E getValue();

}
