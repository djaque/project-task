import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    loaded: false,
    tasks: [],
};

const addTodoReducer = createSlice({
  name: "todos",
  initialState,
  reducers: {
    fetchTasksRequest: (state, action) => {
        state.loaded = false;
        return state;
    },

    fetchTasksSuccess: (state, action) => {
        state.loaded = true;
        state.tasks = action.payload.data;
        return state
    },
    //here we will write our reducer
    //Adding todos
    addTodos: (state, action) => {
      state.tasks.push(action.payload);
      return state;
    },
    //remove todos
    removeTodos: (state, action) => {
      return state.tasks.filter((item) => item.id !== action.payload);
    },
    //update todos
    updateTodos: (state, action) => {
      return state.tasks.map((todo) => {
        if (todo.id === action.payload.id) {
          return {
            ...todo,
            item: action.payload.item,
          };
        }
        return todo;
      });
    },
    //completed
    completeTodos: (state, action) => {
      return state.tasks.map((todo) => {
        if (todo.id === action.payload) {
          return {
            ...todo,
            completed: true,
          };
        }
        return todo;
      });
    },
  },
});

export const {
  addTodos,
  removeTodos,
  updateTodos,
  completeTodos,
  fetchTasksRequest,
    fetchTasksSuccess,
} = addTodoReducer.actions;
export const reducer = addTodoReducer.reducer;