package cl.taskmanager.application.port.in;

import java.util.ArrayList;

import cl.taskmanager.domain.Board;

public interface BoardManagerPort {

	ArrayList<Board> getAll();
	Board create(Board board);
	Board update(Board board) throws Exception;
	void delete(Board board) throws Exception;
	Board get(Long id) throws Exception;
}
