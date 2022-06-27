package ru.netology.manager;


import lombok.Data;

import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.repo.IssueRepository;

import java.util.*;


@NoArgsConstructor
@Data
public class ManagerIssue {
    private IssueRepository repo;

    public ManagerIssue(IssueRepository repo) {
        this.repo = repo;
    }

    public void add(Issue item) {
        repo.save(item);
    }

    public List<Issue> shouldIsOpen() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repo.findAll()) {
            if (item.isOpened()) {
                result.add(item);
            }
        }
        return result;
    }

    public List<Issue> shouldIsClose() {
        List<Issue> result = new ArrayList<>();
        for (Issue item : repo.findAll()) {
            if (!item.isOpened()) {
                result.add(item);
            }
        }
        return result;
    }

    public void Update(int id) {
        Issue result = repo.findById(id);
        if (result.isOpened()) {
            result.setOpened(false);
        } else {
            result.setOpened(true);

        }
    }


    public List<Issue> filterByAuthor(String author) {
        return repo.filterBy(e -> e.getAuthor() == author);
    }

    public List<Issue> filterByLabel(String label) {
        return repo.filterBy(e -> e.getLabel() == label);
    }

    public List<Issue> filterByAssignees(String byAssignees) {
        return repo.filterBy(e -> e.getByAssignees() == byAssignees);
    }


}




