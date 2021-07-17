import React from 'react';

function LogIn() {

    return (
    <>
        <div className="modal fade" id="logInModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style={{zIndex:1200}}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-bs-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 className="modal-title" id="myModalLabel">Modal title</h4>
              </div>
              <div className="modal-body">
                왜 안뜨는 거야 왜 보여줘
              </div>

              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-bs-dismiss="modal">Close</button>
                <button type="button" className="btn btn-primary">Save changes</button>
              </div>
            </div>
          </div>
        </div>

        <button type="button" className="btn btn-primary md-offset-6 md-2"
                        data-bs-toggle="modal" data-bs-target="#logInModal">
                            Log In
                        </button>
    </>
    )

}

export default LogIn;
