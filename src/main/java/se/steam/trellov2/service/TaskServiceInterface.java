package se.steam.trellov2.service;

public interface TaskServiceInterface {

    Task add(Tast task);

    Task get(UUID id);

    Task update(Task task, UUID id);

    Task toggleActive(UUID id);

    List<Task> getByUser(UUID userId);

    List<Task> getByTeam(UUID teamID);

}