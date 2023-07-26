package cl.taskmanager.application.port.out.board;

import java.util.ArrayList;

import cl.taskmanager.domain.Board;

public interface ListBoardPort {
	
	ArrayList<Board> getBoards();
	
}
