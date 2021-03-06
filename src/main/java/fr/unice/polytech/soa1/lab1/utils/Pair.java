package fr.unice.polytech.soa1.lab1.utils;

/**
 * Created by camille on 28/09/15.
 */
public class Pair<L,R> {

    private L left;
    private R right;

    public Pair(){
        left = null;
        right = null;
    }

    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!left.equals(pair.left)) return false;
        return right.equals(pair.right);

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}
