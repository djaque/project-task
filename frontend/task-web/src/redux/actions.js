// actions.js
import { taskApi } from '../API/task';
import { fetchTasksRequest, fetchTasksSuccess } from './reducer';

// Acción asíncrona con Redux Thunk
export const fetchTasks = (loaded) => {
    return async (dispatch) => {
        if (loaded) return;
        dispatch(fetchTasksRequest());
        const resp = await taskApi.get('/task');
        dispatch(fetchTasksSuccess({data: resp.data}));
    };
};

export const addTask = (task) => {
    return async (dispatch) => {
        await taskApi.post('/task', task);
        dispatch(fetchTasks(false));
    };
};

export const updateTask = (task) => {
    return async (dispatch) => {
        await taskApi.put(`/task/${task.id}`, task);
        dispatch(fetchTasks(false));
    };
};

export const deleteTask = (id) => {
    return async (dispatch) => {
        await taskApi.delete(`/task/${id}`);
        dispatch(fetchTasks(false));
    };
};

export const completeTask = (id) => {
    return async (dispatch) => {
        await taskApi.put(`/task/${id}/complete`);
        dispatch(fetchTasks(false));
    };
};

