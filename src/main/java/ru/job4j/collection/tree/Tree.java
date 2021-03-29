package ru.job4j.collection.tree;

import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Node<E> childNode = new Node<>(child);
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent() && !parentNode.get().children.contains(childNode)) {
            parentNode.get().children.add(childNode);
            rsl = true;
        }
        return rsl;
    }

//    public boolean isBinary() {
//        boolean result = true;
//        Queue<Node<E>> data = new LinkedList<>();
//        data.offer(this.root);
//        while (!data.isEmpty()) {
//            Node<E> el = data.poll();
//            if (el.children.size() > 2) {
//                result = false;
//                break;
//            }
//        }
//        return result;
//    }

    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
