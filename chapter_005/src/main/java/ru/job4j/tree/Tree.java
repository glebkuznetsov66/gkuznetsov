package ru.job4j.tree;


import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

/**
 * Class for creating Tree.
 *
 * @author gkuznetsov.
 * @version 0.1.
 * @since 09.10.2017.
 * @param <E> type of element.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E>, Comparator<E> {
    /**
     *Collection with all elements from Tree for Iterator.
     */
    private List<Node<E>> allElements = new ArrayList<>();
    /**
     * Node for storage values and collection of children.
     */
    private Node<E> root = null;
    /**
     * Index for Iterator.
     */
    private int index = 0;

    /**
     * Find parent Node by specified value and parent node.
     * @param parent - parent node value which need find.
     * @param node - node from which need starting search.
     * @return Node.
     */
    private Node<E> findParent(E parent, Node<E> node) {
        Node<E> result = null;
        if (compare(parent, node.value) == 0) {
            result = node;
        } else {
            List<Node<E>> children = node.getChildren();
            if (children.size() > 0) {
                for (Node<E> itm : children) {
                    if (itm == null) {
                        continue;
                    }
                    result = findParent(parent, itm);
                    if (result != null) {
                        break;
                    }
                }
            }
        }
       return result;
    }

    /**
     * Add new element to Tree.
     * @param parent - parent.
     * @param child - child.
     * @return - boolean.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;

        if (root == null && child != null) {
            root = new Node<>(parent);
            root.getChildren().add(new Node<>(child));
            allElements.add(root);
        } else {
            if (!isContains(child, root)) {

                Node<E> parentNode = findParent(parent, root);
                Node<E> childNode = new Node<>(child);

                if (parentNode != null && findParent(child, parentNode) == null) {
                    parentNode.getChildren().add(childNode);
                    allElements.add(childNode);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Add new element into collection.
     * @param e - element.
     * @return boolean.
     */
    public boolean add(E e) {
        boolean result = true;
        if (root == null) {
            root = new Node<>(e, null);
            allElements.add(root);
        } else {
            if (!isContains(e, root)) {
                Node<E> parentNode = getNode(e, root);

                if (parentNode != null) {
                    int index = (parentNode.value.compareTo(e) < 0) ? 1 : 0;
                    parentNode.getChildren().set(index, new Node<>(e, null));
                    allElements.add(parentNode.getChildren().get(index));
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Search Node by value.
     * @param e - node value
     * @param node - start node.
     * @return Node.
     */
    private Node<E> getNode(E e, Node<E> node) {

        int result = e.compareTo(node.value);
        if (result == 0) {
            return null;
        }
        int index = (result < 0) ? 0 : 1;

        Node<E> currentNode = node;

        if (currentNode.getChildren().get(index) != null) {
            currentNode = getNode(e, currentNode.getChildren().get(index));
        }
        return currentNode;
    }

    /**
     * Check that this collection is binary.
     * @return boolean.
     */
    public boolean isBinary() {
        return getAround(root);
    }

    /**
     * Get around this collection and check that each parent has not more 2 children.
     * @param root - start position for check.
     * @return boolean.
     */
    private boolean getAround(Node<E> root) {
        boolean result = true;
        List<Node<E>> children = root.getChildren();
        if (children.size() > 0 && children.size() < 3 || children.size() == 0) {
            for (Node<E> node : root.getChildren()) {
                result = getAround(node);
                if (!result) {
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Return patent child with indexValue.
     * @param parent - parent value.
     * @param indexValue - index of a child in the parent collection.
     * @return E - value.
     */
    public E getChildValue(E parent, int indexValue) {
        return findParent(parent, root).getChildren().get(indexValue).value;
    }

    /**
     * Iterator.
     * @return Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return allElements.size() > index;
            }
            @Override
            public E next() {
               return allElements.get(index++).value;
            }
        };
    }

    /**
     * Comparator.
     * @param o1 - first object.
     * @param o2 - second object.
     * @return int.
     */
    @Override
    public int compare(E o1, E o2) {
        return (o1.equals(o2)) ? 0 : 1;
    }

    /**
     * Equals.
     * @param obj - object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     * Hash code.
     * @return int.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Check that specified element doesn't exist at collection.
     * @param value - checking value.
     * @param node - start node.
     * @return boolean.
     */
    private boolean isContains(E value, Node<E> node) {
        Node<E> currentNode = node;
        E currentValue = node.value;
        E checkingValue = value;
        boolean result = false;

        if (node.value != null && node.value.equals(value)) {
            return true;
        }
        if (node.children == null) {
            result = (compare(currentValue, checkingValue) == 0) ? true : false;
        } else {
            for (Node<E> childNode : node.children) {
                if (childNode != null) {
                    result = isContains(value, childNode);
                }
            }
        }
        return result;
    }


    /**
     * Class for storage value and children.
     * @param <E> - type of element.
     */
    private class Node<E> {
        /**
         * Node children.
         */
        private List<Node<E>> children = new LinkedList<>();
        /**
         * Node value.
         */
        private E value;

        /**
         * Node constructor.
         * @param value - node value.
         */
        Node(E value) {
            this.value = value;
        }

        /**
         * Constructor for Node.
         * @param value - value.
         * @param childValue - child value.
         */
        Node(E value, E childValue) {
            this.value = value;
            children.add(0, (Node<E>) childValue);
            children.add(1, (Node<E>) childValue);
        }

        /**
         * Get children of this node.
         * @return List.
         */
        public List<Node<E>> getChildren() {
            return children;
        }
    }
}
