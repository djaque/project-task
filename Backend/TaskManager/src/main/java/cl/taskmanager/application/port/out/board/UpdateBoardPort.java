package cl.taskmanager.application.port.out.board;

import cl.taskmanager.domain.Board;

public interface UpdateBoardPort {
	Board update(Board board) throws RuntimeException;
}
