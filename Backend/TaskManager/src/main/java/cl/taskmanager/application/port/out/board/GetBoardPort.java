package cl.taskmanager.application.port.out.board;

import cl.taskmanager.domain.Board;

public interface GetBoardPort {
	Board getBoardById(Long id) throws RuntimeException;
}
