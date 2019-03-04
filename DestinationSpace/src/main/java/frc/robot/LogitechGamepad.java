package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

enum TopHatDir {
    NONE(),
    UP(),
    DOWN(),
    LEFT(),
    RIGHT(),
    UP_LEFT(),
    UP_RIGHT(),
    DOWN_LEFT(),
    DOWN_RIGHT();
}

enum LogitchTriggerValue {
    LEFT(2),
    RIGHT(3);

    private int value;

    LogitchTriggerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum LogitchJoyStickValue {
    LEFT_X(0),
    LEFT_Y(1),
    DPAD_LR(4),
    DPAD_UD(5),
    RIGHT_X(4),
    RIGHT_Y(5);

    private int value;

    LogitchJoyStickValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

enum LogitechButtonValue { 
    BUTTON_A(1),
    BUTTON_B(2),
    BUTTON_X(3),
    BUTTON_Y(4),
    BUTTON_LB(5),
    BUTTON_RB(6),
    BUTTON_LT(7),
    BUTTON_RT(8),
    BUTTON_BACK(9),
    BUTTON_START(10),
    LEFT_STICK_BUTTON(11),
    RIGHT_STICK_BUTTON(12);

    private int value;

    LogitechButtonValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class LogitechGamepad {
    private Joystick jstick;

    public LogitechGamepad(int port) {
        jstick = new Joystick(port);
    }

    public boolean isADown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_A.getValue());
    }

    public boolean isBDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_B.getValue());
    }

    public boolean isXDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_X.getValue());
    }

    public boolean isYDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_Y.getValue());
    }

    public boolean isLBDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_LB.getValue());
    }

    public boolean isRBDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_RB.getValue());
    }

    public boolean isLTDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_LT.getValue());
    }

    public boolean isRTDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_RT.getValue());
    }

    public boolean isBackDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_START.getValue());
    }

    public boolean isStartDown() {
        return jstick.getRawButton(LogitechButtonValue.BUTTON_START.getValue());
    }

    public boolean isLeftStickDown() {
        return jstick.getRawButton(LogitechButtonValue.LEFT_STICK_BUTTON.getValue());
    }

    public boolean isRightStickDown() {
        return jstick.getRawButton(LogitechButtonValue.RIGHT_STICK_BUTTON.getValue());
    }

    public double getLeftYAxis() {
        return jstick.getRawAxis(LogitchJoyStickValue.LEFT_Y.getValue());
    }

    public double getRightYAxis() {
        return jstick.getRawAxis(LogitchJoyStickValue.RIGHT_Y.getValue());
    }

    public double getLeftXAxis() {
        return jstick.getRawAxis(LogitchJoyStickValue.LEFT_X.getValue());
    }

    public double getRightXAxis() {
        return jstick.getRawAxis(LogitchJoyStickValue.RIGHT_X.getValue());
    }

    public void rumble(float power) {
        jstick.setRumble(Joystick.RumbleType.kLeftRumble, power);
        jstick.setRumble(Joystick.RumbleType.kRightRumble, power);
    }

    public double getLeftTriggerValue() {
        return jstick.getRawAxis(LogitchTriggerValue.LEFT.getValue());
    }
    
    public double getRightTriggerValue() {
        return jstick.getRawAxis(LogitchTriggerValue.RIGHT.getValue());
    }

    public boolean isLeftTriggerDown(boolean full) {
        return (getLeftTriggerValue() >= (full ? 0.9 : 0.2) ? true : false);
    }
    
    public boolean isRightTriggerDown(boolean full) {
        return (getRightTriggerValue() >= (full ? 0.9 : 0.2) ? true : false);
    }

    public int getDPUpDownRaw() {
        return (int) jstick.getRawAxis(LogitchJoyStickValue.DPAD_UD.getValue());
    }
    
    public int getDPLeftRightRaw() {
        return (int) jstick.getRawAxis(LogitchJoyStickValue.DPAD_LR.getValue());
    }

    public TopHatDir getDPadDir() {
        switch(getDPUpDownRaw()) {
        case 1:
            switch(getDPLeftRightRaw()) {
            case 1:
                return TopHatDir.DOWN_RIGHT;
            case 0:
                return TopHatDir.DOWN;
            case -1:
                return TopHatDir.DOWN_LEFT;
            }
        case 0:
            switch(getDPLeftRightRaw()) {
            case 1:
                return TopHatDir.RIGHT;
            case 0:
                return TopHatDir.NONE;
            case -1:
                return TopHatDir.LEFT;
            }
        case -1:
            switch(getDPLeftRightRaw()) {
            case 1:
                return TopHatDir.UP_RIGHT;
            case 0:
                return TopHatDir.UP;
            case -1:
                return TopHatDir.UP_LEFT;
            }
        }
        return TopHatDir.NONE;
    }
}