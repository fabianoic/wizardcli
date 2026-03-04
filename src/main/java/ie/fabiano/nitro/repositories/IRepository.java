package ie.fabiano.nitro.repositories;

import java.io.IOException;
import java.util.List;

public interface IRepository<E> {

    List<E> getAll() throws IOException, InterruptedException;
}
