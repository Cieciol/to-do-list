package pl.pollub.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Task {

    private final int id;

    private final User taskOwner;

    private final List<User> contributors;

    public List<Integer> getContributorsIds(){
        return new ArrayList<Integer>(contributors.stream()
                                                    .map(User::getId)
                                                    .collect(Collectors.toList()));
    }
    public Integer getOwnerId(){
        return taskOwner.getId();
    }

}
