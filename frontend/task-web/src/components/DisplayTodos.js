import React, { useEffect, useState } from "react";
import { connect, useDispatch } from "react-redux";
import {
  addTodos,
  completeTodos,
  fetchTasksRequest,
  fetchTasksSuccess,
  removeTodos,
  updateTodos,
} from "../redux/reducer";
import TodoItem from "./TodoItem";
import {
  fetchTasks,
  updateTask,
  deleteTask,
  completeTask,
} from "../redux/actions";

const mapStateToProps = (state) => {
  return {
    todos: state.tasks,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    addTodo: (obj) => dispatch(addTodos(obj)),
    removeTodo: (id) => dispatch(removeTodos(id)),
    updateTodo: (obj) => dispatch(updateTodos(obj)),
    completeTodo: (id) => dispatch(completeTodos(id)),
    fetchTasks: (obj) => dispatch(fetchTasks()),
    fetchTasksRequest: (obj) => dispatch(fetchTasksRequest()),
    fetchTasksSuccess: (obj) => dispatch(fetchTasksSuccess(obj)),
    updateTask: (obj) => dispatch(updateTask(obj)),
    deleteTask: (obj) => dispatch(deleteTask(obj)),
    completeTask: (obj) => dispatch(completeTask(obj)),
  };
};

const DisplayTodos = (props) => {
  const dispatch = useDispatch();
  const [loaded, setLoaded] = useState(false);
  useEffect(() => {
    if (loaded) return;
    dispatch(fetchTasks(false));
    setLoaded(true);
  }, [setLoaded, loaded, dispatch]);

  const [sort, setSort] = useState("all");
  return (
    <div className="displaytodos">
      <div className="buttons">
        <button onClick={() => setSort("active")}>Pendientes</button>

        <button onClick={() => setSort("completed")}>Completadas</button>

        <button onClick={() => setSort("all")}>Todas</button>
      </div>
      <div className="listing">
        <ul>
          {props.todos.length > 0 && sort === "active"
            ? props.todos.map((item) => {
                return (
                  item.completed === false && (
                    <TodoItem
                      key={item.id}
                      item={item}
                      removeTodo={props.deleteTask}
                      updateTodo={props.updateTask}
                      completeTodo={props.completeTask}
                    />
                  )
                );
              })
            : null}
          {/* for completed items */}
          {props.todos.length > 0 && sort === "completed"
            ? props.todos.map((item) => {
                return (
                  item.completed === true && (
                    <TodoItem
                      key={item.id}
                      item={item}
                      removeTodo={props.deleteTask}
                      updateTodo={props.updateTask}
                      completeTodo={props.completeTask}
                    />
                  )
                );
              })
            : null}
          {/* for all items */}
          {props.todos.length > 0 && sort === "all"
            ? props.todos.map((item) => {
                return (
                  <TodoItem
                    key={item.id}
                    item={item}
                    removeTodo={props.deleteTask}
                    updateTodo={props.updateTask}
                    completeTodo={props.completeTask}
                  />
                );
              })
            : null}
        </ul>
      </div>
    </div>
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(DisplayTodos);
