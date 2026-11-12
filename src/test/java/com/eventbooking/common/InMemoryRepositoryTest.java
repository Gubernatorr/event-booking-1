package com.eventbooking.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class InMemoryRepositoryTest {

    record Item(String id, String name) implements Identifiable<String> { }

    static class ItemRepository extends InMemoryRepository<Item, String> { }

    private final ItemRepository repository = new ItemRepository();

    @Test
    void savedItemCanBeFoundById() {
        Item item = new Item("1", "first");

        repository.save(item);

        assertThat(repository.findById("1")).contains(item);
    }

    @Test
    void findByIdReturnsEmptyForUnknownId() {
        assertThat(repository.findById("nope")).isEmpty();
    }

    @Test
    void saveWithSameIdOverwrites() {
        repository.save(new Item("1", "old"));
        repository.save(new Item("1", "new"));

        assertThat(repository.findById("1")).contains(new Item("1", "new"));
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void deleteByIdReturnsTrueThenFalse() {
        repository.save(new Item("1", "x"));

        assertThat(repository.deleteById("1")).isTrue();
        assertThat(repository.deleteById("1")).isFalse();
    }

    @Test
    void findAllReturnsUnmodifiableList() {
        repository.save(new Item("1", "x"));
        List<Item> all = repository.findAll();

        assertThatThrownBy(() -> all.add(new Item("2", "y")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}