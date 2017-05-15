package Interpreter;

/**
 * Created by illiant on 2017/5/15.
 */
public class Pointer {
    int value = 0;

    public  void Increment(){
        this.value++;
    }

    public void Decrement(){
        this.value--;
    }

    public char outputChar(){
        return (char) (this.value);
    }

    public int outputNum(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }
}
