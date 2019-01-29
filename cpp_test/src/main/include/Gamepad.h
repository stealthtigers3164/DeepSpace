#pragma once

#include <frc/Joystick.h>

double min(double x, double y) 
{
    return (x < y) ? x : y;
}

double signum(double x)
{
    if (x > 0)
    {
        return 1;
    }
    if (x < 0)
    {
        return -1;
    }

    return 0;
}

class Button {
public:
    Button(frc::Joystick* in_jstick, int in_port) {
        this->port = port;
        this->jstick = in_jstick;
    }
    bool isOn() {
        return jstick->GetRawButton(port);
    }
private:
    int port;
    frc::Joystick* jstick;
};

class gamepadButtons {
public:
    gamepadButtons(frc::Joystick* jstick) {
        BUTTON_A = new Button(jstick, 1);
        BUTTON_B = new Button(jstick, 2);
        BUTTON_X = new Button(jstick, 3);
        BUTTON_Y = new Button(jstick, 4);
        BUTTON_LB = new Button(jstick, 5);
        BUTTON_RB = new Button(jstick, 6);
        BUTTON_LT = new Button(jstick, 5);
        BUTTON_RT = new Button(jstick, 6);
        BUTTON_BACK = new Button(jstick, 7);
        BUTTON_START = new Button(jstick, 8);
        LEFT_STICK_BUTTON = new Button(jstick, 9);
        RIGHT_STICK_BUTTON = new Button(jstick, 10);
    }

    Button* BUTTON_A;
    Button* BUTTON_B;
    Button* BUTTON_X;
    Button* BUTTON_Y;
    Button* BUTTON_LB;
    Button* BUTTON_RB;
    Button* BUTTON_LT;
    Button* BUTTON_RT;
    Button* BUTTON_BACK;
    Button* BUTTON_START;
    Button* LEFT_STICK_BUTTON;
    Button* RIGHT_STICK_BUTTON;
};

enum LeftRightDir {
    DIR_LEFT,
    DIR_RIGHT
};

enum UpDownDir {
    DIR_UP,
    DIR_DOWN
};

/**
 * Joystick wrapper
 * @author jaxon, William Y
 *
 */
class JoystickAxis {
public:
    JoystickAxis(frc::Joystick* jstick, int port) {
        this->port = port;
        this->deadzone = 0.1;
        this->reversed = false;
        this->jstick = jstick;
    }
    
    JoystickAxis(frc::Joystick* jstick, int port, bool rev) {
        this->port = port;
        this->deadzone = 0.1;
        this->reversed = rev;
        this->jstick = jstick;
    }

    double getRaw() {
        return jstick->GetRawAxis(port) * (this->reversed ? -1 : 1);
    }
    
    double getScaled() {
        if(abs(this->getRaw()) <= this->deadzone) return 0;
        return (abs(this->getRaw()) - this->deadzone)/(1 - this->deadzone) * signum(this->getRaw());
    }
    
    /**
     * Sets the deadzone to the greatest (X or Y) value
     * Caps at 0.15 in case this is called when joysticks are being used
     * Since it reads the raw input, only call this from robot initialization, and make sure the joysticks are not being used
     */
    void setDeadzone() {
        this->deadzone = min(abs(this->getRaw()) + 0.01, 0.15);
    }
    
    /**
     * Sets the deadzone to sent value
     * Caps at 0.15, should not need to be more
     * @param dz Deadzone value [0, 0.15]
     */
    void setDeadzone(double dz) {
        this->deadzone = min(abs(dz), 0.15);
    }
    
    /**
     * Returns whether the axis is reversed or not
     * @return true if reversed
     */
    bool isReversed() {
        return this->reversed;
    }
private:
    int port;
    double deadzone;
    bool reversed;
    frc::Joystick* jstick;
};

class UpDownAxis : public JoystickAxis {
public:

    UpDownAxis(frc::Joystick* jstick, int port) : JoystickAxis(jstick, port) 
    {
    }
    
    UpDownAxis(frc::Joystick* jstick, int port, bool rev)  : JoystickAxis(jstick, port, rev)
    {
    }
    
    UpDownDir getDirection() {
        return (this->getRaw() > 0 ? DIR_UP : DIR_DOWN);
    }
    
    double getIntensity() {
        return abs(this->getRaw());
    }
};

class LeftRightAxis : public JoystickAxis {
public:    
    LeftRightAxis(frc::Joystick* jstick, int port) : JoystickAxis(jstick, port) 
    {
    }
    
    /**
     * Set up an axis that can be read
     * @param port port of axis
     * @param rev set true to flip values (mainly for Y axis)
     */
    LeftRightAxis(frc::Joystick* jstick, int port, bool rev) : JoystickAxis(jstick, port, rev) 
    {
    }
    
    LeftRightDir getDirection() {
        return (this->getRaw() >=0 ? DIR_RIGHT : DIR_LEFT);
    }
    
    /**
     * Gives the intensity of the axis (not scaled)
     * Just the absolute value of getRaw()
     * @return [0, 1]
     */
    double getIntensity() {
        return abs(this->getRaw());
    }
};

class gamepadAxes {
public:
    gamepadAxes(frc::Joystick* jstick) {
        LEFT_X = new LeftRightAxis(jstick, 0);
        LEFT_Y = new UpDownAxis(jstick, 1, true);
        RIGHT_X = new LeftRightAxis(jstick, 4);
        RIGHT_Y = new UpDownAxis(jstick, 5, true);
    }

    LeftRightAxis* LEFT_X;
    UpDownAxis* LEFT_Y;
    LeftRightAxis* RIGHT_X;
    UpDownAxis* RIGHT_Y;
    
    void setDeadzones() {
        this->LEFT_X->setDeadzone();
        this->LEFT_Y->setDeadzone();
        this->RIGHT_X->setDeadzone();
        this->RIGHT_Y->setDeadzone();
    }

    void setDeadzones(double dz) {
        this->LEFT_X->setDeadzone(dz);
        this->LEFT_Y->setDeadzone(dz);
        this->RIGHT_X->setDeadzone(dz);
        this->RIGHT_Y->setDeadzone(dz);
    }
};

class gamepadTriggers {
public:    
    gamepadTriggers(frc::Joystick* jstick, int left, int right) {
        this->leftPort = left;
        this->rightPort = right;
        this->jstick = jstick;
    }
    
    /**
     * Get the left trigger's value
     * @return value of left trigger [0,1]
     */
    double getLeftVal() {
        return jstick->GetRawAxis(leftPort);
    }
    
    /**
     * Get the right trigger's value
     * @return value of the right trigger [0,1]
     */
    double getRightVal() {
        return jstick->GetRawAxis(rightPort);
    }
    
    /**
     * Get the triggers acting as an axis
     * Left trigger is negative, right trigger is positive
     * Values cancel each other out, returns net value
     * Ex. Both pressed down = 1 - 1 = 0
     * @return value of the trigger [-1, 1]
     */
    double getAxis() {
        return ((jstick->GetRawAxis(rightPort) - jstick->GetRawAxis(leftPort)));
    }
    
    /**
     * get the net direction of the axis
     * defaults right if zero
     * @return enum dir of axis
     */
    LeftRightDir getDirection() {
        return (this->getAxis() > 0 ? DIR_RIGHT : DIR_LEFT);
    }
    
    bool getLeftPressed(bool full) {
        return (this->getLeftVal() >= (full ? 0.9 : 0.2) ? true : false);
    }
    
    bool getRightPressed(bool full) {
        return (this->getRightVal() >= (full ? 0.9 : 0.2) ? true : false);
    }

private:
    int leftPort;
    int rightPort;
    frc::Joystick* jstick;
};

enum TopHatDir {
    TopHatDir_NONE,
    TopHatDir_UP,
    TopHatDir_DOWN,
    TopHatDir_LEFT,
    TopHatDir_RIGHT,
    TopHatDir_UP_LEFT,
    TopHatDir_UP_RIGHT,
    TopHatDir_DOWN_LEFT,
    TopHatDir_DOWN_RIGHT
};

class gamepadDPad {
private:
    int lrPort;
    int udPort;
    frc::Joystick* jstick;
public:
    gamepadDPad(frc::Joystick* jstick, int lrPort, int udPort) {
        this->lrPort = lrPort;
        this->udPort = udPort;
        this->jstick = jstick;
    }
    /**
     * Gets raw of up and down axis.
     * @return -1, 0, or 1
     */
    int getUpDownRaw() {
        return (int) jstick->GetRawAxis(udPort);
    }
    /**
     * Gets raw of left right axis,
     * @return -1, 0, or 1
     */
    int getLeftRightRaw() {
        return (int) jstick->GetRawAxis(lrPort);
    }
    
    TopHatDir getDir() {
        switch(getUpDownRaw()) {
        case 1:
            switch(getLeftRightRaw()) {
            case 1:
                return TopHatDir_DOWN_RIGHT;
            case 0:
                return TopHatDir_DOWN;
            case -1:
                return TopHatDir_DOWN_LEFT;
            }
        case 0:
            switch(getLeftRightRaw()) {
            case 1:
                return TopHatDir_RIGHT;
            case 0:
                return TopHatDir_NONE;
            case -1:
                return TopHatDir_LEFT;
            }
        case -1:
            switch(getLeftRightRaw()) {
            case 1:
                return TopHatDir_UP_RIGHT;
            case 0:
                return TopHatDir_UP;
            case -1:
                return TopHatDir_UP_LEFT;
            }
        }
        return TopHatDir_NONE;
    }
};

class gamepadRumble {
private:
    frc::Joystick* jstick;
public:
    gamepadRumble(frc::Joystick* jstick)
    {
        this->jstick = jstick;
    }

    void rumbleLeft(float power) {
        jstick->SetRumble(frc::Joystick::RumbleType::kLeftRumble, power);
    }
    
    void rumbleRight(float power) {
        jstick->SetRumble(frc::Joystick::RumbleType::kRightRumble, power);
    }
    
    void rumble(float power) {
        this->rumbleLeft(power);
        this->rumbleRight(power);
    }
    
    void stop() {
        this->rumbleLeft(0);
        this->rumbleRight(0);
    }
};

class Gamepad {
public:
    Gamepad(int port) {
        this->jstick = new frc::Joystick(port);
        this->buttons = new gamepadButtons(jstick);
        this->sticks = new gamepadAxes(jstick);
        this->trigger = new gamepadTriggers(jstick, 2, 3);
        this->tophat = new gamepadDPad(jstick, 4, 5);
        this->rumble = new gamepadRumble(jstick);
    }

    frc::Joystick* jstick;
	gamepadButtons* buttons;
	gamepadAxes* sticks;
	gamepadTriggers* trigger;
	gamepadDPad* tophat;
	gamepadRumble* rumble;
};

	
