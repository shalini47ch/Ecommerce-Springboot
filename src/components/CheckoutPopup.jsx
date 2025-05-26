import PropTypes from "prop-types";
import { Modal, Button } from "react-bootstrap";

const CheckoutPopup = ({
  show,
  handleClose,
  cartItems,
  totalPrice,
  handleCheckout,
}) => {
  return (
    <div className="checkoutPopup">
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Checkout</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="checkout-items">
            {cartItems.length === 0 ? (
              <p>Your cart is empty.</p>
            ) : (
              cartItems.map((item) => (
                <div
                  key={item.id || item.name}
                  className="checkout-item"
                  style={{
                    display: "flex",
                    marginBottom: "10px",
                    alignItems: "center",
                  }}
                >
                  <img
                    src={item.imageUrl}
                    alt={item.name}
                    className="cart-item-image"
                    style={{
                      width: "150px",
                      marginRight: "10px",
                      objectFit: "cover",
                    }}
                  />
                  <div>
                    <b>
                      <p>{item.name}</p>
                    </b>
                    <p>Quantity: {item.quantity}</p>
                    <p>Price: ${(item.price * item.quantity).toFixed(2)}</p>
                  </div>
                </div>
              ))
            )}
            <div>
              <h5
                style={{
                  color: "black",
                  display: "flex",
                  justifyContent: "center",
                  fontSize: "1.3rem",
                  fontWeight: "bold",
                }}
              >
                Total: ${totalPrice.toFixed(2)}
              </h5>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button
            variant="primary"
            onClick={handleCheckout}
            disabled={cartItems.length === 0}
          >
            Confirm Purchase
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

CheckoutPopup.propTypes = {
  show: PropTypes.bool.isRequired,
  handleClose: PropTypes.func.isRequired,
  cartItems: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
      name: PropTypes.string.isRequired,
      imageUrl: PropTypes.string,
      quantity: PropTypes.number.isRequired,
      price: PropTypes.number.isRequired,
    })
  ).isRequired,
  totalPrice: PropTypes.number.isRequired,
  handleCheckout: PropTypes.func.isRequired,
};

export default CheckoutPopup;
