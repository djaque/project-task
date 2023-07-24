import { configureStore,applyMiddleware } from "@reduxjs/toolkit";
import { reducer } from "./reducer";
import thunk from "redux-thunk";

const store = configureStore({
  reducer: reducer,
}, applyMiddleware(thunk));

export default store;