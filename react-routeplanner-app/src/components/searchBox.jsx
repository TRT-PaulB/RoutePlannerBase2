import React from "react";

const SearchBox = ({ value, onChange }) => {
  // note: bind searchQuery to parent, so that the parent can clear the searchBox when appropriate

  console.log("add some value here", value);

  return (
    <input
      type="text"
      name="query"
      className="form-control my-3"
      placeholder="Search text goes here"
      value={value}
      onChange={e => onChange(e.currentTarget.value)}
    />
  );
};

// note: my-3 = margin on the y axis, where 3 is the amount of margin
export default SearchBox;
