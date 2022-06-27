package ru.netology.repo;

import lombok.Data;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Data
public class IssueRepository {

    private List<Issue> items = new ArrayList<>();


    public void save(Issue item) {
        items.add(item);
    }

    public List<Issue> findAll() {
        return this.items;
    }

    public Issue findById(int id) {

        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removedById(int id) {
        items.removeIf(e -> e.getId() == id);

    }

    public void saveAll(Collection<? extends Issue> items) {
        this.items.addAll(items);
    }
    public List<Issue> filterBy(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for(Issue item : items){
            if(filter.test(item)){
                result.add(item);
            }
        }
        return result;
    }
}

