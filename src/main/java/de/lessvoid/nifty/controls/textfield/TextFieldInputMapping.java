package de.lessvoid.nifty.controls.textfield;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import de.lessvoid.nifty.input.NiftyInputMapping;
import de.lessvoid.nifty.input.NiftyStandardInputEvent;
import de.lessvoid.nifty.input.keyboard.KeyboardInputEvent;

/**
 * The input mapping for the {@link de.lessvoid.nifty.controls.TextField}.
 *
 * @author void
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public class TextFieldInputMapping implements NiftyInputMapping {
	/**
	 * Convert a keyboard input event to a {@link NiftyStandardInputEvent}.
	 *
	 * @param inputEvent the keyboard input event that needs to be converted
	 * @return the {@link NiftyStandardInputEvent} that is assigned to the keyboard
	 *         event or {@code null} in case no event is assigned
	 */
	@Nullable
	@Override
	public NiftyStandardInputEvent convert(@Nonnull final KeyboardInputEvent inputEvent) {
		if (inputEvent.isKeyDown()) {
			return handleKeyDownEvent(inputEvent);
		} else {
			return handleKeyUpEvent(inputEvent);
		}
	}

	/**
	 * Translate a keyboard down event to a {@link NiftyStandardInputEvent}
	 * regarding the button that was used at this event.
	 *
	 * @param inputEvent the keyboard input event that needs translation
	 * @return {@link NiftyStandardInputEvent} that is assigned to the keyboard
	 *         event or {@code null} in case no event is assigned
	 */
	@Nullable
	private static NiftyStandardInputEvent handleKeyDownEvent(@Nonnull final KeyboardInputEvent inputEvent) {
		int key = inputEvent.getKey();
		
		if (key == KeyboardInputEvent.KEY_UP)
			return NiftyStandardInputEvent.MoveCursorUp;
		if (key == KeyboardInputEvent.KEY_DOWN)
			return NiftyStandardInputEvent.MoveCursorDown;
		if (key == KeyboardInputEvent.KEY_LEFT)
			return NiftyStandardInputEvent.MoveCursorLeft;
		if (key == KeyboardInputEvent.KEY_RIGHT)
			return NiftyStandardInputEvent.MoveCursorRight;
		if (key == KeyboardInputEvent.KEY_F1)
			return NiftyStandardInputEvent.ConsoleToggle;
		if (key == KeyboardInputEvent.KEY_RETURN)
			return NiftyStandardInputEvent.SubmitText;
		if (key == KeyboardInputEvent.KEY_DELETE)
			return NiftyStandardInputEvent.Delete;
		if (key == KeyboardInputEvent.KEY_BACK)
			return NiftyStandardInputEvent.Backspace;
		if (key == KeyboardInputEvent.KEY_END)
			return NiftyStandardInputEvent.MoveCursorToLastPosition;
		if (key == KeyboardInputEvent.KEY_HOME)
			return NiftyStandardInputEvent.MoveCursorToFirstPosition;
		if (key == KeyboardInputEvent.KEY_LSHIFT || key == KeyboardInputEvent.KEY_RSHIFT)
			return NiftyStandardInputEvent.SelectionStart;
		if (key == KeyboardInputEvent.KEY_TAB)
			return inputEvent.isShiftDown() ? NiftyStandardInputEvent.PrevInputElement
					: NiftyStandardInputEvent.NextInputElement;
		if (key == KeyboardInputEvent.KEY_X ) {
			if (inputEvent.isControlDown()) {
				return NiftyStandardInputEvent.Cut;
			}
		}
			
		if (key == KeyboardInputEvent.KEY_C) {
			if (inputEvent.isControlDown()) {
				return NiftyStandardInputEvent.Copy;
			}
		}
			
		if (key == KeyboardInputEvent.KEY_V) {
			if (inputEvent.isControlDown()) {
				return NiftyStandardInputEvent.Paste;
			}
		}
		if (key == KeyboardInputEvent.KEY_A) {
			if (inputEvent.isControlDown()) {
				return NiftyStandardInputEvent.SelectAll;
			}
		}

		if (!Character.isISOControl(inputEvent.getCharacter()))

		{
			final NiftyStandardInputEvent character = NiftyStandardInputEvent.Character;
			character.setCharacter(inputEvent.getCharacter());
			// System.out.println(inputEvent.getKey());
			return character;
		}
		return null;
	}

	/**
	 * Translate a keyboard key released event into the assigned
	 * {@link NiftyStandardInputEvent}.
	 *
	 * @param inputEvent the keyboard input event that triggered the call of this
	 *                   function
	 * @return the assigned {@link NiftyStandardInputEvent} or {@code null} in case
	 *         no event is assigned
	 */
	@Nullable
	private static NiftyStandardInputEvent handleKeyUpEvent(@Nonnull final KeyboardInputEvent inputEvent) {
		int key = inputEvent.getKey();
		if (key == KeyboardInputEvent.KEY_LSHIFT || key == KeyboardInputEvent.KEY_RSHIFT)
			return NiftyStandardInputEvent.SelectionEnd;
		if (key == KeyboardInputEvent.KEY_ESCAPE)
			return NiftyStandardInputEvent.Escape;
		return null;
		
	}
}
