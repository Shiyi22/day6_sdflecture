package sg.edu.nus.iss;

@FunctionalInterface
public interface MyRunnableInterface<T> {

    T process (T a, T b); // this is a function signature (method, type but no implementation)
    
}
