package cl.taskmanager.adapter.out.persistence;

import cl.taskmanager.domain.Board;
import cl.taskmanager.domain.Task;

public class BoardMapper {
   
    public static Board entityToDomain(BoardEntity boardEntity) {
        Board board = new Board();
        board.setId(boardEntity.getId());
        board.setSubject(boardEntity.getSubject());
        board.setDescription(boardEntity.getDescription());
        board.setCreatedAt(boardEntity.getCreatedAt());
        board.setUpdatedAt(boardEntity.getUpdatedAt());
        for (TaskEntity taskEntity : boardEntity.getTasks()) {
            board.addTask(TaskMapper.entityToDomain(taskEntity));
        }
        return board;
    }

    public static BoardEntity domainToEntity(Board board) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(board.getId());
        boardEntity.setSubject(board.getSubject());
        boardEntity.setDescription(board.getDescription());
        boardEntity.setCreatedAt(board.getCreatedAt());
        boardEntity.setUpdatedAt(board.getUpdatedAt());
        for (Task task : board.getTasks()) {
            boardEntity.addTask(TaskMapper.domainToEntity(task));
        }
        return boardEntity;
    }
}
