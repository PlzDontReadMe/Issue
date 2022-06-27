package ru.netology.manager;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repo.IssueRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@NoArgsConstructor

@Data
class ManagerIssueTest {


    private Issue issue1 = new Issue(1, "Update1", "Me", "1q", "Petr", true);
    private Issue issue2 = new Issue(2, "Update2", "Me1", "2q", "Ivan", false);
    private Issue issue3 = new Issue(3, "Update3", "Me2", "1q", "Petr", false);
    private Issue issue4 = new Issue(4, "Update4", "Me1", "1q", "Vasya", false);
    private Issue issue5 = new Issue(5, "Update5", "Me2", "2q", "Ivan", true);

    private final ManagerIssue manager = new ManagerIssue(new IssueRepository());


    @BeforeEach
    void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    void shouldById() {
        Issue actual = manager.getRepo().findById(2);
        Issue expected = issue2;
        assertEquals(actual, expected);
    }

    @Test
    void shouldByOpened() {
        List<Issue> expected = List.of(issue1, issue5);
        List<Issue> actual = manager.shouldIsOpen();
        assertEquals(expected, actual);
    }

    @Test
    void shouldByClosed() {
        List<Issue> expected = List.of(issue2, issue3, issue4);
        List<Issue> actual = manager.shouldIsClose();
        assertEquals(expected, actual);
    }

    @Test
    void shouldByAuthor() {
        List<Issue> expected = List.of(issue3, issue5);
        List<Issue> actual = manager.filterByAuthor("Me2");
        assertEquals(expected, actual);
    }

    @Test
    void shouldByLabel() {
        List<Issue> expected = List.of(issue1, issue3, issue4);
        List<Issue> actual = manager.filterByLabel("1q");
        assertEquals(expected, actual);
    }

    @Test
    void shouldByAssignees() {
        List<Issue> expected = List.of(issue4);
        List<Issue> actual = manager.filterByAssignees("Vasya");
        assertEquals(expected, actual);
    }

    @Test
    void shouldCloseIssue() {
        manager.Update(1);
        manager.Update(5);
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);
        List<Issue> actual = manager.shouldIsClose();
        assertEquals(expected, actual);
    }

    @Test
    void shouldOpenIssue() {
        manager.Update(2);
        manager.Update(3);
        manager.Update(4);
        List<Issue> expected = List.of(issue1, issue2, issue3, issue4, issue5);
        List<Issue> actual = manager.shouldIsOpen();
        assertEquals(expected, actual);
    }
}


