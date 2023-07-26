package cl.taskmanager.application.port.out.board;

import cl.taskmanager.domain.Board;

public interface CreateBoardPort {
   Board create(Board board); 
}
