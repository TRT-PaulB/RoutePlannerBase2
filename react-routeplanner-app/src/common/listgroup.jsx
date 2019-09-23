import React from "react";

const ListGroup = props => {
  const {
    items,
    textProperty,
    valueProperty,
    selectedItem,
    onItemSelect
  } = props;

  return (
    <ul className="list-group">
      {items.map(item => (
        <li
          key={item[valueProperty] ? item[valueProperty] : ""}
          onClick={() => onItemSelect(item)}
          className={
            item === selectedItem ? "list-group-item active" : "list-group-item"
          }
        >
          {item[textProperty]}
        </li>
      ))}
    </ul>
  );
};

ListGroup.defaultProps = {
  textProperty: "name",
  valueProperty: "_id"
};

/////////////////////// ALTERNATIVE ////////////////
// ==> the benefit of this is to save having to add textProperty and valueProperty fields
// Note the us eof brackets to access properties dynamically (ie. access property text and name specified by parent)
// const ListGroup = (props) => {
//     const {items, textProperty, valueProperty} = props;
//     return <ul className="list-group">
//         {items.map(item => (
//             <li key={item[textProperty]} className="list-group-item">{item[textProperty]}</li>
//         ))}
//     </ul>;
// };
////////////////////////////////////////////////////

export default ListGroup;
