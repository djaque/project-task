package cl.taskmanager.application.port.out.board;

import cl.taskmanager.domain.Board;

public interface DeleteBoardPort {
	void delete(Board board) throws RuntimeException;
}
