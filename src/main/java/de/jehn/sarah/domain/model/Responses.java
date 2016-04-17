package de.jehn.sarah.domain.model;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by sarahjehn on 20.03.16.
 */
public class Responses implements Iterator<Response>, Iterable<Response> {

    private List<Response> responses;

    public Responses(List<Response> responses){
        this.responses = responses;
    }

    public Responses(){}


    public List<Response> getResponses() {
        return responses;
    }


    @Override
    public boolean hasNext() {
        return responses.iterator().hasNext();
    }

    @Override
    public Response next() {
        return responses.iterator().next();
    }

    @Override
    public Iterator<Response> iterator() {
        return responses.iterator();
    }

    @Override
    public void forEach(Consumer<? super Response> action) {
        Objects.requireNonNull(action);
        for (Response t : this) {
            action.accept(t);
        }
    }
}
