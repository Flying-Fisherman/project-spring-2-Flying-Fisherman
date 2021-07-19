import React, { useState } from 'react';
import axios from 'axios'
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';


const useStyles = makeStyles((theme) => ({
  root: {
    '& .MuiTextField-root': {
      margin: theme.spacing(1),
      width: 200,
    },
  },
}));

function NewModal() {

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
      setOpen(true);
    };

    const handleClose = () => {
      setOpen(false);
    };

    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const userIdHandler = (e) => {
        e.preventDefault();
        setUserId(e.target.value);
    };

    const passwordHandler = (e) => {
        e.preventDefault();
        setPassword(e.target.value);
    };

    let body = {
        id: userId,
        password: password,
    };

    const empty = () => {
        setUserId("");
        setPassword("");
    }

    const logIn = async () => {
        axios.post('http://localhost:8080/session', body)
        .then((res) => {
        registerSuccessful(userId, res.data.token)
        console.log(res)
        });
        setLog(true);
    }

    const registerSuccessful = (userId, token) => {
        localStorage.setItem('token', token);
        localStorage.setItem('authenticatedUser', userId);
    }

    const [log, setLog] = useState(false);

    return (
        <>
        { log

        ? <div className="md-offset-6 md-2">
          Hello! { localStorage.getItem('authenticatedUser') }
          </div>

        : <button type="button" onClick={handleOpen} className="btn btn-primary md-offset-6 md-2">
            Log In
          </button>
        }
        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">

          <DialogTitle id="form-dialog-title">
            Log In
          </DialogTitle>

          <DialogContent>

            <TextField
             autoFocus
             required
             margin="dense"
             id="UserID"
             label="Your ID"
             fullWidth
             value={userId}
             onChange={userIdHandler}
             required
            />

            <br/>

            <TextField
             id="UserPassword"
             label="password"
             type="password"
             value={password}
             onChange={passwordHandler}
             required
            />

          </DialogContent>

          <DialogActions>
            <Button onClick={handleClose} color="primary">
              Cancel
            </Button>
            <Button onClick={logIn} color="primary">
              Log In
            </Button>
          </DialogActions>

        </Dialog>

        </>
    )
}

export default NewModal;
