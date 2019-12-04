import React from "react";

const Input = ({ name, label, error, ...rest }) => {
  return (
    <div className="form-group">
      <div className="row">
        <div className="col-2">
          <label class="formInputLabel" htmlFor={name}>{label}</label>
        </div>

        <div className="col">
              <input
              id={name}
              name={name}
              className="form-control"
              {...rest} 
            />

          {error && <div className="alert alert-danger">{error}</div>}
 
        </div>

      </div>
    </div>
  );
};

export default Input;

